import type { CapacitorConfig } from "@capacitor/cli";

const config: CapacitorConfig = {
  appId: "uz.alikuryer.customer",
  appName: "Ali Kuryer",
  webDir: "dist",
  bundledWebRuntime: false,
  android: {
    allowMixedContent: false,
  },
};

export default config;
