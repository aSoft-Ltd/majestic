---
title: Majestic Documentation
bookCollapseSection: false
---

# Majestic Documentation

Majestic is a Compose Multiplatform UI library organized around reusable layout, input, navigation, and product-facing building blocks.

This site is for developers working inside the Majestic codebase or consuming Majestic components in downstream apps.

## Local Development

Install Hugo first.

On Ubuntu:

```bash
sudo snap install hugo
```

Verify the installation:

```bash
hugo version
```

Then run the docs locally from `majestic/documentation` with:

```bash
hugo server --config hugo.toml,hugo.local.toml
```

Once the server starts, open:

```text
http://localhost:1313/
```
