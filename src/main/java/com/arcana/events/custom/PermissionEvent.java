package com.arcana.events.custom;

import com.arcana.utils.user.permissions.ArcanaPermissions;
import com.arcana.utils.user.permissions.PermissionHierarchy;
import com.arcana.utils.user.permissions.PermissionRank;
import org.bukkit.event.Cancellable;

/**
 * The custom events for the ArcanaPermissions system.
 */
public abstract class PermissionEvent extends ArcanaEvent {

    private ArcanaPermissions permissions;

    public PermissionEvent(ArcanaPermissions permissions) {
        this.permissions = permissions;
    }

    public PermissionEvent(String cause, ArcanaPermissions permissions) {
        super(cause);
        this.permissions = permissions;
    }

    /**
     * Any permission event having to do with ArcanaRanks.
     */
    public abstract static class RankEvent extends PermissionEvent implements Cancellable{

        private boolean cancelled;

        private PermissionRank rank;

        public RankEvent(ArcanaPermissions permissions, PermissionRank rank) {
            super(permissions);
            this.rank = rank;
        }

        public RankEvent(String cause, ArcanaPermissions permissions, PermissionRank rank) {
            super(cause, permissions);
            this.rank = rank;
        }

        public PermissionRank getRank() {
            return rank;
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean cancel) {
            this.cancelled = cancel;
        }

        public static class Add extends RankEvent implements Cancellable {

            public Add(ArcanaPermissions permissions, PermissionRank rank) {
                super(permissions, rank);
            }

            public Add(String cause, ArcanaPermissions permissions, PermissionRank rank) {
                super(cause, permissions, rank);
            }
        }

        public static class Remove extends RankEvent {

            public Remove(ArcanaPermissions permissions, PermissionRank rank) {
                super(permissions, rank);
            }

            public Remove(String cause, ArcanaPermissions permissions, PermissionRank rank) {
                super(cause, permissions, rank);
            }
        }

        public static class Promote extends RankEvent {

            private PermissionHierarchy hierarchy;

            public Promote(ArcanaPermissions permissions, PermissionRank rank, PermissionHierarchy hierarchy) {
                super(permissions, rank);
                this.hierarchy = hierarchy;
            }

            public Promote(String cause, ArcanaPermissions permissions, PermissionRank rank, PermissionHierarchy hierarchy) {
                super(cause, permissions, rank);
                this.hierarchy = hierarchy;
            }

            public PermissionHierarchy getHierarchy() {
                return hierarchy;
            }
        }

        public static class Demote extends RankEvent {

            private PermissionHierarchy hierarchy;

            public Demote(ArcanaPermissions permissions, PermissionRank rank, PermissionHierarchy hierarchy) {
                super(permissions, rank);
                this.hierarchy = hierarchy;
            }

            public Demote(String cause, ArcanaPermissions permissions, PermissionRank rank, PermissionHierarchy hierarchy) {
                super(cause, permissions, rank);
                this.hierarchy = hierarchy;
            }

            public PermissionHierarchy getHierarchy() {
                return hierarchy;
            }
        }

    }
}
