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
/**
 * Base infrastructure for configuration loaders
 *
 * <p>A configuration loader is responsible for converting between the location
 * of a serialized form (a file, string, or URL) and a
 * {@link org.spongepowered.configurate.ConfigurationNode}. Most loader
 * implementations will want to extend
 * {@link org.spongepowered.configurate.loader.AbstractConfigurationLoader} in
 * order to gain some standard abilities. Any binary formats will probably want
 * to find other solutions, since the abstract loader assumes in many places
 * that configuration files are text files.
 */
@DefaultQualifier(NonNull.class)
package org.spongepowered.configurate.loader;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
