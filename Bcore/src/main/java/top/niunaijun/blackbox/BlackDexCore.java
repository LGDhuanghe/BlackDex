package top.niunaijun.blackbox;

import android.content.Context;
import android.net.Uri;

import java.io.File;

import top.niunaijun.blackbox.app.configuration.ClientConfiguration;
import top.niunaijun.blackbox.core.system.dump.IBDumpMonitor;
import top.niunaijun.blackbox.entity.pm.InstallResult;

/**
 * Created by Milk on 2021/5/22.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class BlackDexCore {
    public static final String TAG = "BlackBoxCore";

    private static final BlackDexCore sBlackDexCore = new BlackDexCore();

    public static BlackDexCore get() {
        return sBlackDexCore;
    }

    public void doAttachBaseContext(Context context, ClientConfiguration clientConfiguration) {
        BlackBoxCore.get().doAttachBaseContext(context, clientConfiguration);
    }

    public void doCreate() {
        BlackBoxCore.get().doCreate();
    }

    public boolean dumpDex(String packageName) {
        InstallResult installResult = BlackBoxCore.get().installPackage(packageName);
        if (installResult.success) {
            boolean b = BlackBoxCore.get().launchApk(packageName);
            if (!b) {
                BlackBoxCore.get().uninstallPackage(installResult.packageName);
            }
            return b;
        } else {
            return false;
        }
    }

    public boolean dumpDex(File file) {
        InstallResult installResult = BlackBoxCore.get().installPackage(file);
        if (installResult.success) {
            boolean b = BlackBoxCore.get().launchApk(installResult.packageName);
            if (!b) {
                BlackBoxCore.get().uninstallPackage(installResult.packageName);
            }
            return b;
        } else {
            return false;
        }
    }

    public boolean dumpDex(Uri file) {
        InstallResult installResult = BlackBoxCore.get().installPackage(file);
        if (installResult.success) {
            boolean b = BlackBoxCore.get().launchApk(installResult.packageName);
            if (!b) {
                BlackBoxCore.get().uninstallPackage(installResult.packageName);
            }
            return b;
        } else {
            return false;
        }
    }

    public void registerDumpMonitor(IBDumpMonitor monitor) {
        BlackBoxCore.getBDumpManager().registerMonitor(monitor.asBinder());
    }

    public void unregisterDumpMonitor(IBDumpMonitor monitor) {
        BlackBoxCore.getBDumpManager().unregisterMonitor(monitor.asBinder());
    }
}
