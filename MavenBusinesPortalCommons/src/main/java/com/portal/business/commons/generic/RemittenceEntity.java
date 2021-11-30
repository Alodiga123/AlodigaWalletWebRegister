package com.portal.business.commons.generic;

import com.portal.business.commons.exceptions.TableNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;



public abstract class RemittenceEntity implements RemittenceGenericEntity, Serializable {

        public abstract Object getPk();
    
	public abstract String getTableName() throws TableNotFoundException;

	@Override
	public String toString() {
            //return this.toString();
            return "";
		/*StringBuilder b = new StringBuilder("[");
		b.append("ClassName=" + getClass().getSimpleName());
		b.append("{");
		for (Method m : getClass().getMethods()) {
			String methodName = m.getName();
			if (methodName.contains("get") && !methodName.contains("getClass") && !methodName.contains("getPK")) {
				try {
					b.append(m.getName().substring(3));
					b.append("=");

					Object o = m.invoke(this, null);
					if (o != null) {
						if (o instanceof AbstractDistributionEntity) {
							b.append(o.getClass().getSimpleName());
						} else if (o instanceof List) {
							b.append("Size list: " + ((List) o).size());
						} else {
							b.append(m.invoke(this, null));
						}
					}
					b.append("| ");
				} catch (Exception e) {
					if (e instanceof IllegalArgumentException) {
					} else {
						e.printStackTrace();
					}

				}
			}
		}
		b.append(']');
		b.append("}");
		return b.toString();*/
	}

	public String getTableName(Class<?> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		return table.name();
	}

}
