#!/usr/bin/env bash
# Prints a hash of the working tree's changes against HEAD, tracked and untracked.
set -euo pipefail
cd "$(git rev-parse --show-toplevel)"
{
    git diff HEAD --binary
    git ls-files --others --exclude-standard | while read -r file; do
        if [ -L "$file" ]; then echo "$file -> $(readlink "$file")"; else shasum "$file"; fi
    done
} | shasum | cut -d' ' -f1
