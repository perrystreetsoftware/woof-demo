@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.typography

import com.perrystreet.woof.designsystem.atomic._primitives.typography.TypographyPrimitives

object TypographyRoles {
    val Default = Typography(
        display = Typography.Display(
            h1 = TypographyPrimitives.Bold.Size32,
            h2 = TypographyPrimitives.Bold.Size24,
            h3 = TypographyPrimitives.Bold.Size20,
            h4 = TypographyPrimitives.Bold.Size16,
            h5 = TypographyPrimitives.Bold.Size14,
            h6 = TypographyPrimitives.Bold.Size12,
        ),
        subhead = Typography.Subhead(
            p1 = TypographyPrimitives.SemiBold.Size16,
            p2 = TypographyPrimitives.SemiBold.Size14,
            p3 = TypographyPrimitives.SemiBold.Size12,
        ),
        body = Typography.Body(
            p1 = TypographyPrimitives.Regular.Size16,
            p2 = TypographyPrimitives.Regular.Size14,
            p3 = TypographyPrimitives.Regular.Size12,
            p4 = TypographyPrimitives.Regular.Size10,
        ),
    )
}
