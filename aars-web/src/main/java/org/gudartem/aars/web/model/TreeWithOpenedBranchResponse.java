package org.gudartem.aars.web.model;

import org.gudartem.aars.model.abstraction.BaseDto;
import org.gudartem.aars.model.dto.ThemeDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class TreeWithOpenedBranchResponse {
    private Collection<ThemeDto> tree;
    private Collection<BaseDto<UUID>> open;
    private Collection<BaseDto<UUID>> active;

    public TreeWithOpenedBranchResponse() {
        this.open = new ArrayList<>();
        this.active = new ArrayList<>();
    }

    public Collection<ThemeDto> getTree() {
        return tree;
    }

    public void setTree(Collection<ThemeDto> tree) {
        this.tree = tree;
    }

    public Collection<BaseDto<UUID>> getOpen() {
        return open;
    }

    public void setOpen(Collection<BaseDto<UUID>> open) {
        this.open = open;
    }

    public Collection<BaseDto<UUID>> getActive() {
        return active;
    }

    public void setActive(Collection<BaseDto<UUID>> active) {
        this.active = active;
    }
}
