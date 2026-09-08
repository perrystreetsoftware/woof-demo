import SwiftUI

public enum TypographyRoles {
    public static let `default` = Typography(
        display: Typography.Display(
            h1: TypographyPrimitives.Bold.size32,
            h2: TypographyPrimitives.Bold.size24,
            h3: TypographyPrimitives.Bold.size20,
            h4: TypographyPrimitives.Bold.size16,
            h5: TypographyPrimitives.Bold.size14,
            h6: TypographyPrimitives.Bold.size12
        ),
        subhead: Typography.Subhead(
            p1: TypographyPrimitives.SemiBold.size16,
            p2: TypographyPrimitives.SemiBold.size14,
            p3: TypographyPrimitives.SemiBold.size12
        ),
        body: Typography.Body(
            p1: TypographyPrimitives.Regular.size16,
            p2: TypographyPrimitives.Regular.size14,
            p3: TypographyPrimitives.Regular.size12,
            p4: TypographyPrimitives.Regular.size10
        )
    )
}
