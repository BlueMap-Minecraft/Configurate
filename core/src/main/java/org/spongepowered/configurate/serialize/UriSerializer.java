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
package org.spongepowered.configurate.serialize;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Predicate;

final class UriSerializer extends ScalarSerializer<URI> {

    UriSerializer() {
        super(URI.class);
    }

    @Override
    public URI deserialize(final Type type, final Object obj) throws SerializationException {
        final String plainUri = obj.toString();
        try {
            return new URI(plainUri);
        } catch (final URISyntaxException e) {
            throw new CoercionFailedException(obj, "URI");
        }
    }

    @Override
    public Object serialize(final URI item, final Predicate<Class<?>> typeSupported) {
        return item.toString();
    }

}
