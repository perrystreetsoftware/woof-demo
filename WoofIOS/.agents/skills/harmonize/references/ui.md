# ui rules

Rules for screens, adapters, and view modifiers. Read before you create or change a view.

- [Every Adapter delegates rendering to a Screen view.](ui/adapters-call-screens.md)
- [Custom view modifiers are private.](ui/custom-modifiers-are-private.md)
- [Only *Adapter views inject ViewModels.](ui/only-adapters-inject-view-models.md)
- [Text the user sees comes from string resources, never from literals in presentation code.](ui/presentation-text-comes-from-resources.md)
- [Every Screen has a #Preview wrapped in ThemedScreenPreview with an explicit theme.](ui/screens-have-device-previews.md)
- [Feature UI only composes Template*, Org*, Mol*, and Atom* components and never touches layout modifiers or design tokens.](ui/screens-use-design-system-components.md)
- [Screen views use a Template* as their root layout.](ui/screens-use-templates.md)
- [Views and screen-layer extensions in presentation packages do not use if statements or optional map.](ui/view-layer-does-not-use-if-statements.md)

