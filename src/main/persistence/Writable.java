package persistence;

import org.json.JSONObject;

// modeled from provided sample jsonserializationdemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
