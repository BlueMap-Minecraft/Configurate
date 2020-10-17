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

import org.spongepowered.configurate.util.CheckedFunction;

import java.lang.reflect.Type;
import java.util.function.BiFunction;
import java.util.function.Predicate;

final class FunctionScalarSerializer<T> extends ScalarSerializer<T> {

    private final CheckedFunction<Object, T, SerializationException> deserializer;
    private final BiFunction<T, Predicate<Class<?>>, Object> serializer;

    FunctionScalarSerializer(final Type type,
            final CheckedFunction<Object, T, SerializationException> deserializer, final BiFunction<T, Predicate<Class<?>>, Object> serializer) {
        super(type);
        this.deserializer = deserializer;
        this.serializer = serializer;
    }

    @Override
    public T deserialize(final Type type, final Object obj) throws SerializationException {
        try {
            return this.deserializer.apply(obj);
        } catch (final SerializationException ex) {
            ex.initType(type);
            throw ex;
        }
    }

    @Override
    public Object serialize(final T item, final Predicate<Class<?>> typeSupported) {
        return this.serializer.apply(item, typeSupported);
    }

}
