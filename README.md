# Douch Proxy

**Douch** is a next-generation, high-performance Minecraft proxy, fully compatible with all versions of Paper server and Minecraft. Designed and engineered by Sunpowder, Douch aims to surpass BungeeCord and Velocity in performance, compatibility, and extensibility.

## Features
- Full support for all Minecraft versions (legacy to latest) via one version of the proxy
- Ultra-fast Netty-based networking engine
- Advanced protocol translation and compatibility
- Plugin API for extensibility
- Robust security and anti-exploit features
- Monitoring, logging, and metrics
- **Standalone API module for plugin development** ([see DouchAPI on GitHub](https://github.com/MONDERASDOR/DouchAPI))
- Advanced chat management (filtering, commands, admin commands)
- Firewall and backend server management

## Requirements
- Java 17 or higher
- Maven 3.6+

## Build & Run
1. **Clone or download this repository.**
2. **Build the proxy:**
   ```sh
   mvn clean package
   ```
   This will produce a shaded JAR at:
   ```
   target/original-douch-proxy-1.0.0-shaded.jar
   ```
3. **Run the proxy:**
   ```sh
   java -jar target/original-douch-proxy-1.0.0-shaded.jar
   ```
   You should see:
   ```
   ========================================
    Douch Proxy v1.0.0
    High-performance Minecraft Proxy
    Developed by MONDERASDOR
   ========================================
   Starting proxy...
   ```
   The proxy will now listen for Minecraft clients and manage backend Paper servers as configured.

**Note:**
- If you see warnings about deprecated methods in Netty, these are harmless for now.
- If you encounter `SecurityException: Invalid signature file digest`, ensure you are running the *shaded* JAR (`original-douch-proxy-1.0.0-shaded.jar`).
- The regular `douch-proxy-1.0.0.jar` will NOT run directly (no main class or dependencies bundled).
- an error will happen if you used normal `mvn clean package`.. just ignore it because the proxy jar will get created anyways and you'll find it. if you are on linux no errors will happen and still you'll find the .jar
## Advanced Features
- **Chat Management:**
  - Prohibited/banned word filtering
  - Player chat commands
  - Admin commands (kick, mute, etc)
- **Firewall:**
  - Connection limiting
  - IP bans and allowlists
- **Backend Management:**
  - Add/remove Paper servers at runtime
  - Live player/session tracking

## Plugin Development
Douch provides a full-featured, standalone API for plugin and integration developers. The API is published as a Maven dependency via JitPack:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
<dependency>
  <groupId>com.github.MONDERASDOR</groupId>
  <artifactId>DouchAPI</artifactId>
  <version>v1.0.0</version>
</dependency>
```

- See the [DouchAPI README](https://github.com/MONDERASDOR/DouchAPI#readme) for full usage, events, and examples.

<<<<<<< HEAD
## Troubleshooting
- **Proxy does not start**: Make sure you are running the shaded JAR, and that Java 17+ is installed.
- **Signature errors**: Only run the `original-douch-proxy-1.0.0-shaded.jar` file in `target/`.
- **Build errors**: Delete all JARs in `target/` and re-run `mvn clean package`.
=======
>>>>>>> a24eeeb40786490442727789f6ecca858d132485

## License
Copyright (c) Listedroot. All rights reserved.
MIT license 
free forever
maintained forever
