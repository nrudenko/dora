package com.github.nrudenko.orm.compiler.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Adapter {
    public final String adapterClass;
    public final String adapterName;
    public final String constructor;
    public final Set<String> imports = new LinkedHashSet<>();

    public Adapter(String adapterClass, String adapterName, String constructor) {
        this.adapterClass = adapterClass;
        this.adapterName = adapterName;
        this.constructor = constructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adapter adapter = (Adapter) o;

        if (adapterClass != null ? !adapterClass.equals(adapter.adapterClass) : adapter.adapterClass != null)
            return false;
        if (adapterName != null ? !adapterName.equals(adapter.adapterName) : adapter.adapterName != null) return false;
        if (constructor != null ? !constructor.equals(adapter.constructor) : adapter.constructor != null) return false;
        return !(imports != null ? !imports.equals(adapter.imports) : adapter.imports != null);

    }

    @Override
    public int hashCode() {
        int result = adapterClass != null ? adapterClass.hashCode() : 0;
        result = 31 * result + (adapterName != null ? adapterName.hashCode() : 0);
        result = 31 * result + (constructor != null ? constructor.hashCode() : 0);
        result = 31 * result + (imports != null ? imports.hashCode() : 0);
        return result;
    }
}
