package de.binetsky.myHandler;

/**
 * Created by abinetsky on 12/16/16.
 */
public class JsonTarget {
    private Target target;

    public JsonTarget(Target target) {
        this.target = target;
    }

    public String toJson() {
        return String.format("{'name': '%s', 'value': '%d'}", target.name(), target.value());
    }
}
