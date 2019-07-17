package org.gudartem.aars.impl;

import org.gudartem.aars.api.DefaultService;
import org.gudartem.aars.db.DatabaseConfiguration;

public class DefaultServiceImpl implements DefaultService {
    @Override
    public void process() {
        DatabaseConfiguration dbConf = new DatabaseConfiguration();
    }
}
