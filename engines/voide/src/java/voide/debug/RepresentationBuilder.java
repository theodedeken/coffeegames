/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package voide.debug;

import java.util.List;

public class RepresentationBuilder {

    private String repr;

    public RepresentationBuilder(String className) {
        repr = String.format("%s {\n", className);
    }

    public RepresentationBuilder field(String fieldName, Representable value) {
        repr += String.format("  %s = %s\n", fieldName, value.repr());
        return this;
    }

    public RepresentationBuilder field(String fieldName, Object value) {
        repr += String.format("  %s = %s\n", fieldName, value.toString());
        return this;
    }

    public RepresentationBuilder field(String fieldName, List<Object> value) {
        repr += String.format("  %s = [\n", fieldName);
        for (Object el : value) {
            if (el instanceof Representable) {
                repr += String.format("    %s\n", ((Representable) el).repr());
            } else {
                repr += String.format("    %s\n", el.toString());
            }
        }
        repr += "  ]\n";
        return this;
    }

    public String build() {
        return repr + "}";
    }
}
