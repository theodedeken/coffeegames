/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package voide.debug;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TestRepresentationBuilder {
    @Test
    void basicTest() {
        RepresentationBuilder builder = new RepresentationBuilder("Test");
        builder.field("field1", "one");
        builder.field("field2", Arrays.asList("one", "two"));
        String repr1 = builder.build();
        builder.field("field3", "three");
        String repr2 = builder.build();

        assertEquals(repr1, "Test {\n  field1 = one\n  field2 = [\n    one\n    two\n  ]\n}");
        assertEquals(repr2, "Test {\n  field1 = one\n  field2 = [\n    one\n    two\n  ]\n  field3 = three\n}");
    }

}
