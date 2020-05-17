package org.gudartem.aars.api.model;

import org.gudartem.aars.db.model.entity.Theme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class TreeWithOpenedBranchResult {
    private Collection<Theme> tree;
    private Collection<UUID> open;
    private UUID active;

    public TreeWithOpenedBranchResult(Collection<Theme> tree, UUID active) {
        this.tree = tree;
        this.open = new ArrayList<>();
        this.active = active;
    }

    public Collection<Theme> getTree() {
        return tree;
    }

    public void setTree(Collection<Theme> tree) {
        this.tree = tree;
    }

    public Collection<UUID> getOpen() {
        return open;
    }

    public void setOpen(Collection<UUID> open) {
        this.open = open;
    }

    public UUID getActive() {
        return active;
    }

    public void setActive(UUID active) {
        this.active = active;
    }
}
