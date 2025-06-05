# Douch Proxy

Douch Proxy is a high-performance, next-generation Minecraft proxy designed for full compatibility with all versions of Paper server and Minecraft. Built with a focus on performance, security, and extensibility, Douch Proxy aims to provide a robust alternative to BungeeCord and Velocity.

---

## Table of Contents
- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Features](#features)
- [Requirements](#requirements)
- [Running Douch Proxy](#running-douch-proxy)
- [Advanced Features](#advanced-features)
- [Security & Anti-Exploit](#security--anti-exploit)
- [Metrics & Monitoring](#metrics--monitoring)
- [Backend Management](#backend-management)
- [Plugin Development](#plugin-development)
- [FAQ](#faq)
- [Support & Community](#support--community)
- [Configuration](#configuration)
- [Contribution Guide](#contribution-guide)
- [License](#license)

---

## Project Overview
Douch Proxy is designed to be the modern, secure, and extensible core of your Minecraft network. Unlike legacy proxies, it is built from the ground up for performance, security, and ease of use. It supports all Minecraft versions with a single binary and provides a robust API for plugin development.

## Why Douch Proxy Was Created
Douch Proxy was built to address the limitations and pain points of existing Minecraft proxies like BungeeCord and Velocity. The main motivations include:
- **Unified Version Support:** Existing proxies often struggle with supporting all Minecraft versions seamlessly. Douch Proxy aims to provide true multi-version support in a single binary.
- **Modern Security:** Many legacy proxies lack robust, modern security features. Douch Proxy integrates advanced firewall, rate limiting, and anti-exploit measures by default.
- **Performance:** By leveraging Netty and a streamlined architecture, Douch Proxy is designed for high throughput and low latency, even under heavy load.
- **Extensibility:** The plugin API is designed to be modern, clean, and easy to use, enabling rapid development of custom features and integrations.
- **Operational Simplicity:** Douch Proxy uses a single configuration file and manages multiple backend servers in one process, reducing operational complexity.

## Why Use Douch Proxy?
- **All-in-One Solution:** Manage multiple backend servers, advanced chat, firewall, and metrics from a single proxy.
- **Security First:** Built-in protections against common Minecraft exploits, DDoS, and abuse.
- **Modern Plugin API:** Develop plugins with a clean, modern API designed for today's needs.
- **Performance:** Optimized for low latency and high concurrency, suitable for large networks.
- **Unified Management:** Consistent chat, command, and security policies across all backend servers.
- **Active Development:** Douch Proxy is actively maintained and open to community feedback.

## When Douch Proxy Might Not Be Right for You
- **Legacy Plugin Compatibility:** If you rely on BungeeCord or Velocity plugins, Douch Proxy is not compatible with those APIs.
- **Hot-Reload Requirements:** Douch Proxy does not support hot-reloading of plugins or configuration; restarts are required for changes.
- **Non-Paper Backends:** While optimized for Paper, support for other server types may be limited or experimental.
- **Minimalist Needs:** If you only need basic proxying without advanced features, a simpler proxy may suffice.
- **Cutting-Edge Features:** As a newer project, some edge-case features or integrations may not yet be available compared to older, more established proxies.

## Architecture
- **Single Process, Multi-Backend:** Manages multiple backend Paper servers in a single process. No need for multiple proxy instances.
- **Netty-Based Pipeline:** Uses Netty for all networking, with a modular pipeline for protocol handling, security, chat, and metrics.
- **Dynamic Protocol Handling:** Custom decoders/encoders and handshake logic allow seamless support for all Minecraft versions.
- **Configuration-Driven:** All core settings are managed via a single `config.properties` file.
- **Plugin API:** Extensible via a modern plugin API (see [DouchAPI](https://github.com/MONDERASDOR/DouchAPI)).

## Features
- Supports all Minecraft versions (legacy to latest) with a single proxy version
- High-speed Netty-based networking
- Advanced protocol translation and compatibility
- Plugin API for extensibility ([DouchAPI on GitHub](https://github.com/MONDERASDOR/DouchAPI))
- Comprehensive security and anti-exploit measures
- Monitoring, logging, and metrics
- Advanced chat management (filtering, commands, admin tools)
- Integrated firewall and backend server management

## Requirements
- Java 17 or higher
- Maven 3.6+

## Running Douch Proxy
Douch Proxy is designed to be simple to run, but its architecture differs from traditional Minecraft proxies like BungeeCord or Velocity:

- **Single Process, Multi-Backend:** Douch Proxy runs as a single process and manages multiple backend Paper servers internally. You do not need to run separate processes for each backend or use additional plugins for basic proxying.
- **Configuration-Driven:** All core settings, including backend servers, ports, and firewall options, are managed through the `config.properties` file. There is no need for additional YAML or JSON configuration files or plugin folders.
- **No Plugin Hot-Reload:** Unlike BungeeCord, Douch Proxy does not support hot-reloading plugins or configuration changes at runtime. Changes to the configuration require a restart of the proxy.
- **Modern Networking:** Douch Proxy uses a modern Netty-based networking engine and advanced protocol translation, allowing it to support all Minecraft versions with a single binary.
- **No Legacy Plugin API:** Douch Proxy does not use the BungeeCord or Velocity plugin APIs. Instead, it provides its own API ([DouchAPI](https://github.com/MONDERASDOR/DouchAPI)) for plugin development.

### How to Run
1. Build the proxy as described above.
2. Edit `config.properties` to match your environment and backend servers.
3. Start the proxy with:
   ```sh
   java -jar target/original-douch-proxy-1.0.0-shaded.jar
   ```
4. Connect your Minecraft client to the proxy's IP and port (as set in `config.properties`).

**Note:**
- The proxy must be restarted to apply configuration changes.
- All backend Paper servers should be configured in `config.properties` and should not be run with their own proxy plugins (like BungeeCord or Velocity plugins).

## Advanced Features
- **Chat Management:**
  - Word filtering, anti-spam, mute, and censor features (see `ChatFilter`, `ChatAntiSpam`, `ChatMuteManager`, `ChatWordCensor`)
  - Player chat commands and admin commands (see `ChatCommandHandler`, `AdminCommandHandlerNetty`)
  - Private messaging, chat replay, and channel management
- **Firewall:**
  - Connection limiting per IP (`ConnectionLimiter`)
  - IP bans, allowlists, ASN, GeoIP, and port blocking (`FirewallManager`, `BlacklistManager`, `WhitelistManager`, `ASNBlocker`, `GeoIPBlocker`, `PortBlocker`)
  - Rate limiting and DoS protection (`RateLimiter`, `DoSProtector`)
- **Backend Management:**
  - Add or remove Paper servers at runtime (`BackendServerRegistry`)
  - Live player and session tracking (`PlayerManager`, `PlayerSession`)
  - Health checks and failover (`BackendHealthMonitor`, `BackendBalancer`)

## Security & Anti-Exploit
- **Firewall Integration:** All connections are checked against firewall rules before being accepted.
- **Rate Limiting:** Prevents abuse by limiting requests per IP.
- **Blacklist/Whitelist:** Easily ban or allow specific IPs, usernames, or countries.
- **Protocol Blocking:** Block specific protocols or exploit attempts.
- **Metrics & Auditing:** All connection attempts and suspicious activity can be logged and monitored.

## Metrics & Monitoring
- **Traffic Metrics:** Track bytes read/written (`TrafficMetrics`)
- **Connection Metrics:** Track active and total connections (`ConnectionMetrics`)
- **Latency Metrics:** Track average latency (`LatencyMetrics`)
- **Player Metrics:** Track online/max players (`PlayerMetrics`)
- **Packet Metrics:** Track packets in/out (`PacketMetrics`)
- **Export & Alert:** Metrics can be exported, persisted, and used for alerting (`MetricExporter`, `MetricAlertManager`)

## Backend Management
- **Dynamic Server Registry:** Add/remove backend servers without restarting the proxy (`BackendServerRegistry`)
- **Health Monitoring:** Automatic health checks and failover (`BackendHealthMonitor`)
- **Load Balancing:** Distribute players across servers (`BackendBalancer`)
- **Live Tracking:** Track player sessions and server status in real time

## Plugin Development
Douch Proxy provides a standalone API for plugin and integration development. The API is available as a Maven dependency via JitPack:

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

- **Plugin Lifecycle:** Implement `Plugin` interface with `onEnable`, `onDisable`, etc.
- **Event System:** Listen for player join/quit, server switch, command registration, proxy ping, and chat events. All events implement `getTimestamp()` and `getName()`. Use `EventPriority` and `EventBusImpl` for advanced event handling.
- **Command Registration:** Register custom commands via the API.
- **Context Access:** Access proxy internals (config, logger, scheduler, etc.) via `PluginContext`.
- **Example Plugins:** See [DouchAPI README](https://github.com/MONDERASDOR/DouchAPI#readme) for usage and examples.

## FAQ
**Q: Does Douch Proxy support all Minecraft versions?**
A: Yes, it is designed to support all versions with a single binary.

**Q: Can I use BungeeCord or Velocity plugins?**
A: No, Douch Proxy uses its own modern API for plugins. Only Paper plugins that you setup in the Paper backend server are supported.

**Q: How do I add backend servers?**
A: Edit `config.properties` or use the backend management commands (if enabled).

**Q: Is hot-reload supported?**
A: No, changes require a restart for safety and stability.

**Q: How do I monitor proxy health?**
A: Use the built-in metrics system or export metrics for external monitoring.

**Q: Why does Douch Proxy have more handlers and add features that already exist in the Paper server?**
A: Douch Proxy implements its own handlers for chat, firewall, compression, encryption, login, status, and more to provide advanced security, compatibility, and control at the proxy level. This allows Douch Proxy to:
- Filter, block, or modify traffic before it reaches backend servers, protecting against exploits and attacks.
- Support all Minecraft versions and protocols, even if backend servers do not.
- Provide unified chat filtering, command handling, and admin tools across all servers.
- Enforce global rate limits, firewalls, and connection policies that Paper cannot enforce alone.
- Collect metrics and monitor traffic at the network edge, not just inside the backend server.
- Provide a modern event system for plugins, including event priorities and new events like ProxyPingEvent and CommandRegisterEvent.
These features are necessary because a proxy must handle and secure traffic before it reaches the backend, and because not all backend servers may have the same plugins or configuration.

**Q: Can I use Douch Proxy with non-Paper backend servers?**
A: Douch Proxy is optimized for Paper, but may work with other Minecraft server types that use compatible protocols. Full feature support is only guaranteed with Paper.

**Q: Does Douch Proxy support plugin hot-reloading?**
A: No, for stability and security, plugins and configuration changes require a proxy restart.

**Q: How do I update Douch Proxy?**
A: Download the latest release, replace the JAR, and restart the proxy. Always back up your configuration first.

**Q: Where can I find example plugins?**
A: See the [DouchAPI README](https://github.com/MONDERASDOR/DouchAPI#readme) for example plugins and usage guides.

**Q: How do I report bugs or request features?**
A: Open an issue on the [GitHub Issues page](https://github.com/MONDERASDOR/DouchProxy/issues).

## Support & Community
- **GitHub Issues:** [Douch Proxy Issues](https://github.com/MONDERASDOR/DouchProxy/issues)
- **Discord:** (Add your Discord link here)
- **Documentation:** [DouchAPI README](https://github.com/MONDERASDOR/DouchAPI#readme)

## Configuration
When you first run the proxy, a `config.properties` file will be created in the working directory if it does not exist. You can edit this file to configure the proxy. The available options are:

- `proxy.port`: The port the proxy listens on (default: 25565)
- `proxy.host`: The IP address to bind (default: 0.0.0.0 for all interfaces)
- `firewall.enabled`: Enable or disable the firewall (`true` or `false`)
- `max.connections`: Maximum simultaneous connections per IP (default: 10)
- `backend.servers`: Comma-separated list of backend Paper server addresses (e.g., `127.0.0.1:25566,127.0.0.1:25567`)

Example `config.properties`:
```
# Douch Proxy Configuration
proxy.port=25565
proxy.host=0.0.0.0
firewall.enabled=true
max.connections=10
backend.servers=127.0.0.1:25566
```

## Contribution Guide
We welcome contributions! To contribute:
- Fork the repository and create a feature branch.
- Follow the code style and document your changes.
- Submit a pull request with a clear description.
- For plugin contributions, see the [DouchAPI](https://github.com/MONDERASDOR/DouchAPI).

## License
Copyright (c) Listedroot. All rights reserved.

MIT License
