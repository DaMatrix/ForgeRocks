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

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rocksdb.RocksDB;

/**
 * @author DaPorkchop_
 */
@Mod("forgerocks")
public class ForgeRocks1_16 {
    public ForgeRocks1_16() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        Logger logger = LogManager.getLogger();
        logger.info("Loading rocksdb native libraries...");

        try {
            RocksDB.loadLibrary(); //ensure native lib is loaded
        } catch (Throwable t) {
            logger.error("Unable to load rocksdb native libraries!", t);
            throw new RuntimeException("Unable to load rocksdb native libraries!", t);
        }
    }
}
