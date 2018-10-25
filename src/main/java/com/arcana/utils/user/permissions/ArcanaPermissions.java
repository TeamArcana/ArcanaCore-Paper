package com.arcana.utils.user.permissions;

import com.arcana.events.custom.ArcanaEvent;
import com.arcana.events.custom.PermissionEvent;
import com.arcana.utils.ArcanaKey;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is the permission container that should be given to players. PermissionHierarchy should be static/final instances
 * that simply interact with an instance of this class. PermissionNodes and PermissionRanks should not be directly
 * given to players, but rather all filtered through, and applied to, an instance of ArcanaPermissions.
 */
public class ArcanaPermissions {

    private List<PermissionRank> ranks;

    public ArcanaPermissions() {
        ranks = new CopyOnWriteArrayList<>();
    }

    public boolean hasRank(ArcanaKey key){
        for(PermissionRank rank: ranks){
            if(rank.getKey() == key){
                return true;
            }
        }
        return false;
    }

    public boolean hasRank(PermissionRank rank){
        for(PermissionRank r: ranks){
            if(r == rank){
                return true;
            }
        }
        return false;
    }

    public Optional<PermissionRank> getRank(ArcanaKey key){
        for(PermissionRank rank: ranks){
            if(rank.getKey() == key){
                return Optional.of(rank);
            }
        }
        return Optional.empty();
    }

    public Optional<PermissionRank> getRank(PermissionRank rank){
        for(PermissionRank r: ranks){
            if(r == rank){
                return Optional.of(r);
            }
        }
        return Optional.empty();
    }

    public boolean hasPermission(PermissionNode node){
        for(PermissionRank rank: ranks){
            for(PermissionNode p: rank.getPermissions()){
                if(p == node){
                    return true;
                }
            }
        }
        return false;
    }

    public void addRank(PermissionRank rank, boolean suppressEvent){
        if(!hasRank(rank)){
            if(!suppressEvent){
                PermissionEvent.RankEvent.Add event = new PermissionEvent.RankEvent.Add(this, rank);
                ArcanaEvent.callEvent(event);

                if(!event.isCancelled()){
                    ranks.add(rank);
                }
            } else {
                ranks.add(rank);
            }
        }
    }

    public void removeRank(PermissionRank rank, boolean suppressEvent){
        if(!suppressEvent){
            PermissionEvent.RankEvent.Remove event = new PermissionEvent.RankEvent.Remove(this, rank);
            ArcanaEvent.callEvent(event);

            if(!event.isCancelled()){
                ranks.remove(rank);
            }
        } else {
            ranks.remove(rank);
        }
    }
}
