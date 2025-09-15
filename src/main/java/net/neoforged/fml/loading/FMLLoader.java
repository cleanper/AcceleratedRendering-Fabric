package net.neoforged.fml.loading;

import net.fabricmc.loader.api.FabricLoader;
import org.annotation.Delete;

@Delete(reason = "目前本模组并不需要的文件")
public class FMLLoader {

    public static final   FabricLoader DELEGATE   =   FabricLoader.getInstance();

    public static boolean isProduction() {
        return !DELEGATE.isDevelopmentEnvironment();
    }
}
