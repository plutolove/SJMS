package com.example.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;


public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("user", User.class);
		arp.addMapping("job", Job.class);
		arp.addMapping("note", Note.class);
	}
}

