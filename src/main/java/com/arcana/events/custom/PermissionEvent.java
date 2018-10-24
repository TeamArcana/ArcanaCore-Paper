package com.arcana.events.custom;

import com.arcana.utils.user.permissions.ArcanaPermissions;
import com.arcana.utils.user.permissions.PermissionHierarchy;
import com.arcana.utils.user.permissions.PermissionRank;

public abstract class PermissionEvent extends ArcanaEvent {

    private ArcanaPermissions permissions;

    public PermissionEvent(ArcanaPermissions permissions) {
        this.permissions = permissions;
    }

    public PermissionEvent(String cause, ArcanaPermissions permissions) {
        super(cause);
        this.permissions = permissions;
    }

    public abstract static class RankEvent extends PermissionEvent{

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

        public static class Add extends RankEvent {

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