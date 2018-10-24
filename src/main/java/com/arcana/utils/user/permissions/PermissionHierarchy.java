package com.arcana.utils.user.permissions;

import com.arcana.events.custom.ArcanaEvent;
import com.arcana.events.custom.PermissionEvent;
import com.arcana.utils.ArcanaKey;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A PermissionHierarchy is an easy way to create an order of PermissionRanks. Easily used for hierarchies such as:
 * - Staff
 * - Guilds
 * - Parties
 * etc.
 *
 * Only one instance of each PermissionHierarchy should be instantiated and is recommended to be kept in a
 * Manager<PermissionHierarchy>
 */
public class PermissionHierarchy {

    private ArcanaKey key;
    private List<PermissionRank> ranks;

    public PermissionHierarchy(ArcanaKey key) {
        this.key = key;
        ranks = new CopyOnWriteArrayList<>();
    }

    public PermissionHierarchy(ArcanaKey key, PermissionRank... ranks){
        this.key = key;
        this.ranks = new CopyOnWriteArrayList<>();

        if(ranks != null){
            this.ranks.addAll(Arrays.asList(ranks));
        }
    }

    public boolean is(ArcanaKey key){
        return this.key == key;
    }

    public Optional<PermissionRank> getPromotion(PermissionRank current){
        if(contains(current) && ranks.indexOf(current) < ranks.size() - 1){
            return Optional.of(ranks.get(ranks.indexOf(current) + 1));
        }
        return Optional.empty();
    }

    public Optional<PermissionRank> getDemotion(PermissionRank current){
        if(contains(current) && ranks.indexOf(current) > 0){
            return Optional.of(ranks.get(ranks.indexOf(current) - 1));
        }
        return Optional.empty();
    }

    public boolean promote(PermissionRank current, ArcanaPermissions owner, boolean suppressEvent){
        Optional<PermissionRank> p = getPromotion(current);

        if(p.isPresent()){
            owner.removeRank(current, suppressEvent);
            owner.addRank(p.get(), suppressEvent);

            if(!suppressEvent){
                PermissionEvent.RankEvent.Promote event = new PermissionEvent.RankEvent.Promote(owner, p.get(), this);
                ArcanaEvent.callEvent(event);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean demote(PermissionRank current, ArcanaPermissions owner, boolean suppressEvent){
        Optional<PermissionRank> p = getDemotion(current);

        if(p.isPresent()){
            owner.removeRank(current, suppressEvent);
            owner.addRank(p.get(), suppressEvent);

            if(!suppressEvent){
                PermissionEvent.RankEvent.Demote event = new PermissionEvent.RankEvent.Demote(owner, p.get(), this);
                ArcanaEvent.callEvent(event);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(PermissionRank rank){
        return ranks.contains(rank);
    }
}
