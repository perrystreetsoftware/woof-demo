# designsystem rules

Rules for the design system: atoms, molecules, organisms, templates, roles, and tokens. Read before you create or change a UI component.

- [Public views are prefixed by their layer: Atom*, Mol*, Org*, Template*.](designsystem/atomic-components-use-layer-prefix.md)
- [Atoms are built from SwiftUI primitives only; they never call other atoms.](designsystem/atoms-do-not-compose-other-atoms.md)
- [Atomic layers only depend on the layers below them: _Primitives -> _Tokens -> Atoms -> Molecules -> Organisms -> Templates.](designsystem/design-system-atomic-dependencies.md)
- [The design system knows nothing about domain models, UI models, DI, or image loaders.](designsystem/design-system-does-not-depend-on-app-layers.md)
- [The design system is feature-agnostic: names and the resources it references describe shape and data, never a feature.](designsystem/design-system-is-feature-agnostic.md)
- [Roles never carry feature data; they are enums whose entries resolve theme tokens.](designsystem/do-not-use-roles-for-feature-data.md)
- [Molecules combine at least two atoms.](designsystem/molecules-must-combine-at-least-two-atoms.md)
- [Molecules, organisms and templates build on atoms, never on the raw SwiftUI components the atoms wrap.](designsystem/no-raw-swift-ui-components.md)
- [An organism that contains every part of an existing organism calls that organism instead of rebuilding it.](designsystem/organisms-reuse-existing-organisms.md)
- [Component roles live in a Roles folder next to their component.](designsystem/roles-live-in-roles-folder.md)
- [Screen padding roles may only be applied by Templates.](designsystem/screen-padding-is-only-used-in-templates.md)
- [Component states live in a State folder next to their component.](designsystem/states-live-in-state-folder.md)
- [Templates expose slots, never layout values.](designsystem/templates-do-not-expose-modifiers.md)
- [Templates pad with PaddingRoles.Screen, never PaddingRoles.Element.](designsystem/templates-pad-with-screen-roles.md)
- [Views never hardcode colors.](designsystem/ui-components-do-not-use-hardcoded-values-color.md)
- [Views never build static gradients inline.](designsystem/ui-components-do-not-use-hardcoded-values-gradient.md)
- [Views never hardcode point values.](designsystem/ui-components-do-not-use-hardcoded-values-point.md)

