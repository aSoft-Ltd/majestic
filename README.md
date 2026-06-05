Majestic is a Kotlin Multiplatform Compose UI library for building shared product interfaces across supported platforms.

## Documentation

Majestic documentation is generated with Dokka.

For local documentation work, run one of these commands from the `majestic` root:

```bash
./gradlew :dokkaGenerate
```

or, when you specifically want the public HTML site:

```bash
./gradlew :dokkaGeneratePublicHtml
```

The generated site is written to:

```text
build/dokka/html
```