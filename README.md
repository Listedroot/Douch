# Douch Proxy
![Douch Logo](logo.svg)
Douch Proxy is a high-performance, next-generation Minecraft proxy solution offering full compatibility with all Minecraft and Paper server versions. Engineered for performance, security, and extensibility, Douch Proxy serves as a modern alternative to traditional proxies like BungeeCord and Velocity.

---
consider supporting the project to help maintain it !

[![Donate](https://liberapay.com/assets/widgets/donate.svg)](https://liberapay.com/YourUsername/donate)
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

## 🚀 Project Overview

Douch Proxy is the modern, secure, and extensible foundation for your Minecraft network infrastructure. Built from the ground up with performance and security as top priorities, it delivers a seamless experience across all Minecraft versions through a single binary. The proxy's robust plugin API enables extensive customization and extension of its core functionality.

## 💡 Why Choose Douch Proxy?

Douch Proxy was created to overcome the limitations of existing Minecraft proxy solutions. Here's what sets it apart:

### 🔄 Unified Version Support
- Single binary supporting all Minecraft versions from legacy to latest
- Seamless protocol translation and compatibility
- No version-specific configurations needed

### 🔒 Enterprise-Grade Security
- Built-in protection against common exploits and attacks
- Advanced firewall with IP whitelisting/blacklisting
- Rate limiting and DoS protection
- Secure authentication and encryption

### ⚡ Performance Optimized
- Netty-based networking for maximum throughput
- Low-latency architecture
- Efficient resource utilization
- Handles thousands of concurrent connections

### 🧩 Extensible Architecture
- Modern, clean plugin API
- Comprehensive event system
- Easy integration with existing infrastructure
- Simplified plugin development

### 🛠️ Simplified Operations
- Single configuration file
- Built-in metrics and monitoring
- Dynamic backend management
- Minimal resource footprint

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
- **Plugin API:** Extensible via a modern plugin API (see [DouchAPI](https://github.com/Listedroot/DouchAPI)).

## ✨ Key Features

### Core Functionality
- **Universal Version Support**: Single binary for all Minecraft versions
- **High-Performance Networking**: Built on Netty for maximum efficiency
- **Unified Configuration**: Centralized settings management
- **Plugin System**: Extensible through custom plugins

### Advanced Capabilities
- **Dynamic Protocol Handling**: Automatic version detection
- **Load Balancing**: Intelligent player distribution
- **Health Monitoring**: Automatic failover and recovery
- **Comprehensive Metrics**: Built-in monitoring and analytics
- **Chat Management**: Advanced filtering and commands
- **Security Suite**: Firewall, rate limiting, and exploit protection

## 🛠️ System Requirements

### Prerequisites
- Java 17 or higher (recommended: Java 17 LTS)
- Maven 3.6+ (for building from source)
- At least 1GB RAM (2GB+ recommended for production)
- Linux/Windows/macOS (Linux recommended for production)

## 🚀 Getting Started

### Quick Start
1. Download the latest release from our [GitHub releases](https://github.com/Listedroot/Douch/releases)
2. Create a `config.properties` file (or let it generate on first run)
3. Start the proxy:
   ```bash
   java -jar douch-proxy-1.0.0-shaded.jar
   ```
4. Connect your Minecraft client to the proxy's IP and port

### Key Differences from Other Proxies

| Feature | Douch Proxy | BungeeCord | Velocity |
|---------|-------------|------------|----------|
| Architecture | Single process, multi-backend | Multi-process | Multi-process |
| Configuration | Single properties file | Multiple YAML files | Multiple files |
| Hot Reload | No | Yes | Partial |
| Protocol Support | All versions | Version-dependent | Version-dependent |
| Resource Usage | Low | Medium | Medium |
| Plugin API | Modern, async | Legacy | Modern |

### Configuration Basics

Edit the `config.properties` file to customize:

```properties
# Network settings
proxy.port=25565
proxy.host=0.0.0.0
proxy.online-mode=true

# Backend servers (comma-separated)
backend.servers=127.0.0.1:25566

# Security settings
firewall.enabled=true
max.connections=10
rate-limit=1000
```

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
  <groupId>com.github.Listedroot</groupId>
  <artifactId>DouchAPI</artifactId>
  <version>v1.0.0</version>
</dependency>
```

- **Plugin Lifecycle:** Implement `Plugin` interface with `onEnable`, `onDisable`, etc.
- **Event System:** Listen for player join/quit, server switch, command registration, proxy ping, and chat events. All events implement `getTimestamp()` and `getName()`. Use `EventPriority` and `EventBusImpl` for advanced event handling.
- **Command Registration:** Register custom commands via the API.
- **Context Access:** Access proxy internals (config, logger, scheduler, etc.) via `PluginContext`.
- **Example Plugins:** See [DouchAPI README](https://github.com/Listedroot/DouchAPI#readme) for usage and examples.

## ❓ Frequently Asked Questions

### General

<details>
<summary>Does Douch Proxy support all Minecraft versions?</summary>
Yes! Douch Proxy is designed to support all Minecraft versions with a single binary, from the earliest to the latest releases. It handles protocol translation automatically.
</details>

<details>
<summary>Can I use BungeeCord or Velocity plugins?</summary>
No, Douch Proxy uses its own modern API for plugins. However, you can use Paper plugins on your backend servers. For proxy-side functionality, you'll need to use plugins specifically designed for Douch Proxy.
</details>

### Setup & Configuration

<details>
<summary>How do I add backend servers?</summary>
Edit the `config.properties` file and update the `backend.servers` property with comma-separated addresses (e.g., `backend.servers=hub.example.com:25565,minigames.example.com:25566`). Restart the proxy to apply changes.
</details>

<details>
<summary>How do I run the proxy in offline mode?</summary>
Start the proxy with the `--offline` flag or set `online-mode=false` in the configuration file. This disables authentication with Mojang's servers.
</details>

### Security

<details>
<summary>What encryption does the proxy use?</summary>
Douch Proxy uses:
- **AES-128/256** for encrypted packet communication
- **RSA 2048-bit** for secure key exchange in online mode
- **SSL/TLS** for secure backend connections (when configured)
</details>

<details>
<summary>How does rate limiting work?</summary>
The proxy enforces configurable rate limits to prevent abuse:
- Connection rate limiting (max connections per IP)
- Packet rate limiting (packets per second)
- Chat message throttling

Adjust these in the `config.properties` file.
</details>

### Troubleshooting

<details>
<summary>How do I view debug logs?</summary>
Enable debug mode using one of these methods:
1. Start with `--debug` flag
2. Set `debug=true` in config
3. Configure your logging framework (e.g., log4j2.xml) with DEBUG level
</details>

<details>
<summary>Why are players getting disconnected?</summary>
Common reasons include:
- Firewall blocking connections
- Rate limiting being triggered
- Backend server timeouts
- Version mismatches

Check the proxy logs for specific error messages.
</details>

## 🤝 Support & Community

### Getting Help
- **Documentation**: [DouchAPI Documentation](https://github.com/Listedroot/DouchAPI#readme)

### Reporting Issues
When reporting issues, please include:
1. Proxy version and Java version
2. Relevant configuration
3. Steps to reproduce
4. Error logs (if applicable)

## 🛠️ Configuration Reference

### Network Settings
```properties
# Bind address (0.0.0.0 for all interfaces)
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
- For plugin contributions, see the [DouchAPI](https://github.com/Listedroot/DouchAPI).

## License
Copyright (c) Listedroot. All rights reserved.

MIT License
