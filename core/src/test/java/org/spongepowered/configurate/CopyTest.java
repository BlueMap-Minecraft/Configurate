/*
 * Configurate
 * Copyright (C) zml and Configurate contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spongepowered.configurate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CopyTest {

    @Test
    void testSimpleCopy() {
        final ConfigurationNode node = BasicConfigurationNode.root();
        node.getNode("test").setValue(5);
        node.getNode("section", "val1").setValue(true);
        node.getNode("section", "val2").setValue("TEST");
        final ConfigurationNode list = node.getNode("section2", "alist");
        list.appendListNode().setValue("value1");
        list.appendListNode().setValue("value2");

        final ConfigurationNode copy = node.copy();

        assertNotSame(node, copy);
        assertEquals(node, copy);

        assertFalse(node.isVirtual());
        assertFalse(copy.isVirtual());

        assertEquals(5, copy.getNode("test").getValue());
        assertEquals(true, copy.getNode("section", "val1").getValue());
        assertEquals("TEST", copy.getNode("section", "val2").getValue());
        assertEquals(Arrays.asList("value1", "value2"), copy.getNode("section2", "alist").getValue());

        // change value on original
        node.getNode("section", "val2").setValue("NOT TEST");

        // test it's still the same on copy
        assertEquals("TEST", copy.getNode("section", "val2").getValue());

        // change value on copy
        copy.getNode("section", "val2").setValue("zzz");

        // test it's still the same on original
        assertEquals("NOT TEST", node.getNode("section", "val2").getValue());
    }

    @Test
    void testCopyPaths() {
        final ConfigurationNode node = BasicConfigurationNode.root();
        node.getNode("test").setValue(5);
        node.getNode("section", "val1").setValue(true);
        node.getNode("section", "val2").setValue("TEST");

        final ConfigurationNode original = node.getNode("section");
        final ConfigurationNode copy = original.copy();

        assertNotNull(original.getParent());
        assertNull(copy.getParent());

        final ConfigurationNode originalVal = original.getNode("val1");
        final ConfigurationNode copyVal = copy.getNode("val1");

        assertEquals(2, originalVal.getPath().size());
        assertEquals(1, copyVal.getPath().size());

        assertNotNull(originalVal.getParent());
        assertNotNull(copyVal.getParent());
        assertNotSame(originalVal.getParent(), copyVal.getParent());
    }

}
