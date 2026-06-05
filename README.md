# majestic

Majestic is a Kotlin Multiplatform Compose UI library for building shared product interfaces across supported platforms.

## Documentation

Majestic documentation is generated with Dokka and published into the repository's `docs` directory.

From the `majestic` root, run:

```bash
./gradlew :docs
```

This task generates the Dokka HTML site and copies the built files to:

```text
docs
```

Open `docs/index.html` to inspect the generated documentation locally.

If you only need Dokka's raw generated output, use:

```bash
./gradlew :dokkaGeneratePublicHtml
```

That writes the intermediate site to `build/dokka/html`.