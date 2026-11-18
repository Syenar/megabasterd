# MegaBasterd — Syenar fork

MegaBasterd was originally created by [Tonikelope](https://github.com/tonikelope). This unofficial fork is maintained by Syenar. The original copyright notices and [GPLv3 license](LICENSE) are preserved. See the [original project](https://github.com/tonikelope/megabasterd) and its [contributors](https://github.com/tonikelope/megabasterd/graphs/contributors).

Use this credit in Syenar fork release notes: **MegaBasterd was originally created by [Tonikelope](https://github.com/tonikelope/megabasterd). This fork is maintained by Syenar.**

## Settings shortcut update (2026-11-18)

Adds a compact settings cog immediately after DEBUG on the main page. It opens the existing Settings dialog, including the normal save and account cleanup behavior. The icon has a localized tooltip, an accessible name, hover feedback, keyboard focus, and zoom scaling. Opening Settings preserves the selected transfer or debug page.

The published build is based on upstream 8.61. Download the runnable JAR from this fork's Releases page and run it with Java 8 or later. This fork is maintained independently and is not an official Tonikelope release.

Validation: Maven package build and an isolated Swing smoke check covering click and keyboard activation, selection preservation, accessible labeling, zoom scaling, and scrollable narrow layouts. Full live desktop capture could not be verified because the desktop capture service reported a GPU failure.

To run the smoke check after building:

```text
javac -encoding UTF-8 -cp target/classes -d target/test-classes scripts/SettingsShortcutSmoke.java
java -Djava.awt.headless=true -cp "target/classes;target/test-classes" com.tonikelope.megabasterd.SettingsShortcutSmoke
```

Use `:` instead of `;` in the classpath on Linux/macOS.

---

<h1>MegaBasterd</h1>

[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity) [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

<p align="center"><i>"If it compiles, it's good; if it boots up, it's perfect." (Linus Torvalds)</i></p>
<p align="center"><a href="https://github.com/Syenar/megabasterd/releases/latest" target="_blank"><img src="https://raw.githubusercontent.com/tonikelope/megabasterd/master/src/main/resources/images/mbasterd_logo_git.png"></a></p>
<h2 align="center"><a href="https://github.com/Syenar/megabasterd/releases/latest" target="_blank"><b>Download latest build</b></a></h2>
<h3 align="center"><i>Note: MegaBasterd jar version requires <a href="https://adoptium.net/es/temurin/releases/?version=11" target="_blank">Java 8 or later</a>.</i></h3>
<p align="center"><a href="https://github.com/Syenar/megabasterd/releases/latest" target="_blank"><img src="https://raw.githubusercontent.com/tonikelope/megabasterd/master/src/main/resources/images/linux-mac-windows.png"></a></p>
<p align="center"><a href="https://github.com/tonikelope/megabasterd/issues/397"><b>Would you like to help by translating MegaBasterd into your favorite language?</b></a></p>


![Screnshot](/src/main/resources/images/mbasterd_screen.png)




<p align="center"><a href="https://youtu.be/5TkBXT7osQI"><b>MegaBasterd DEMO</b></a></p>

<p align="center"><img src="https://raw.githubusercontent.com/tonikelope/megabasterd/master/coffee.png"><br><img src="https://raw.githubusercontent.com/tonikelope/megabasterd/master/src/main/resources/images/ethereum_toni.png"></p>

<p align="center"><a href="https://github.com/tonikelope/megabasterd/issues/385#issuecomment-1019215670">BONUS: Why the f*ck has MegaBasterd stopped downloading?</a></p>

<p align="center"><b>IMPORTANT:</b> You are not authorized to use MegaBasterd in any way that violates <a href="https://mega.io/es/terms"><b>MEGA's terms of use</b></a>.</p>
