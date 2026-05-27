# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the BIOP team's extension catalog for QuPath (an open-source bioimage analysis application). The catalog is a `catalog.json` file that QuPath's built-in extension manager reads to let users discover, install, and manage BIOP extensions.

## Repository Structure

- **`catalog.json`** — The catalog file consumed by QuPath. This is the primary artifact of the repo. It follows the schema defined by [qupath/extension-catalog-model](https://github.com/qupath/extension-catalog-model).
- **`biop-catalog-generator.groovy`** — A Groovy script meant to be run inside QuPath's script editor to regenerate `catalog.json`. Before running, the `savingFolder` path at the top must be updated to your local checkout.
- **`.github/workflows/validate-catalog.yml`** — CI workflow that validates `catalog.json` against the `extension_catalog_model` Python package on push to `main` and on PRs.

## Catalog JSON Schema

Each extension entry has: `name`, `description`, `author`, `homepage`, `starred`, and a list of `releases`. Each release has:
- `name` — version tag (e.g. `"v0.12.0"`)
- `main_url` — download URL for the extension JAR or ZIP
- `required_dependency_urls` / `optional_dependency_urls` — Maven or GitHub URLs for dependency JARs
- `version_range` — `{ min, max, excludes }` specifying compatible QuPath versions

## Common Tasks

### Validate the catalog locally
```bash
pip install git+https://github.com/qupath/extension-catalog-model.git
python -c "from extension_catalog_model.model import *; Catalog.model_validate_json(open('catalog.json').read())"
```

### Regenerate catalog.json from the Groovy script
Open `biop-catalog-generator.groovy` in QuPath's script editor (requires QuPath v0.7.0+), update the `savingFolder` variable to point to your local `catalog.json` path, and run it.

## Key Conventions

- Extensions target QuPath version ranges: `v0.6.0–v0.6.0`, `v0.6.0–v0.7.0`, or `v0.7.0–v0.7.0`. When adding a new release, pick the appropriate range.
- Some extensions (Warpy, ABBA) have shared Maven dependencies (imglib2, imglib2-realtransform, jitk-tps, ejml, jama) listed in `required_dependency_urls`. Keep dependency versions consistent across extensions that share them.
- Simple extensions (Cellpose, Spotiflow, BIOP) ship as self-contained ZIPs or JARs with no extra dependencies.
- ABBA bundles Warpy as a required dependency — when Warpy's version changes, ABBA's dependency URL must be updated to match.
- Version tags use a `v` prefix in the catalog (e.g. `"v0.12.0"`), but some older Warpy/ABBA download URLs omit the `v` in the path segment.