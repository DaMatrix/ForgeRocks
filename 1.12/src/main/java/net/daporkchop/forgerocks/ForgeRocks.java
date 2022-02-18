/*
 *   Copyright 2022 rocksdb developers
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package net.daporkchop.forgerocks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.rocksdb.RocksDB;

/**
 * @author DaPorkchop_
 */
@Mod(modid = ForgeRocks.MODID,
        acceptableRemoteVersions = "*",
        useMetadata = true)
public class ForgeRocks {
    public static final String MODID = "forgerocks";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        event.getModLog().info("Loading rocksdb native libraries...");

        try {
            RocksDB.loadLibrary(); //ensure native lib is loaded
        } catch (Throwable t) {
            event.getModLog().error("Unable to load rocksdb native libraries!", t);
            throw new RuntimeException(t);
        }
    }
}
