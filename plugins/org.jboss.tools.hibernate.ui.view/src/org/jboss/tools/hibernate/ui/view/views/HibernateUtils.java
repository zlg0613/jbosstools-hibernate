package org.jboss.tools.hibernate.ui.view.views;

import java.util.Iterator;

import org.hibernate.mapping.Column;
import org.hibernate.mapping.ForeignKey;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.RootClass;
import org.hibernate.mapping.Table;

public class HibernateUtils {
	
	public static String getTableName(String catalog, String schema, String name) {
		return (catalog != null ? catalog + "." : "") + (schema != null ? schema + "." : "") + name;
	}

	public static String getTableName(Table table) {
		return getTableName(table.getCatalog(), table.getSchema(), table.getName());
	}

	public static String getPersistentClassName(PersistentClass rootClass) {
		if (rootClass == null) {
			return "";
		} else { 
			return rootClass.getEntityName() != null ? rootClass.getEntityName() : rootClass.getClassName();
		}
	}
	
	public static String getPersistentClassName(String className) {
		if (className == null) {
			return "";
		} else if (className.indexOf(".") < 0) {
			return "default." + className;
		}
		return className;
	}
	
	public static boolean isPrimaryKey(Column column){
		Table table = getTable(column);
		if(table != null){
			if(table.getPrimaryKey() != null){
				if(table.getPrimaryKey().containsColumn(column)) return true;
			}
		}
		return false;
	}
	
	public static boolean isForeignKey(Column column){
		Table table = getTable(column);
		if(table != null){
			Iterator iter = table.getForeignKeyIterator();
			while(iter.hasNext()){
				ForeignKey fk = (ForeignKey)iter.next();
				if(fk.containsColumn(column)) return true;
			}
		}
		return false;
		
	}
	
	public static Table getTable(Column column){
		if(column.getValue() != null)
			return column.getValue().getTable();
		return null;
	}
}