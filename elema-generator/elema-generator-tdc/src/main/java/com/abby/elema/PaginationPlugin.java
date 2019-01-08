package com.abby.elema;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;

import java.util.List;

/**
 * @author: Abby
 */
public class PaginationPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    public static void main(String[] args){
        String generatorConfig=PaginationPlugin.class.getClassLoader().getResource("generatorConfig.xml").getFile();
        String[] arg = {"-configfile", generatorConfig};
        ShellRunner.main(arg);
    }
}
