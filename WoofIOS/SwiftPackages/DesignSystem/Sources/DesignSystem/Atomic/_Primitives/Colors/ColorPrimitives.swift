import SwiftUI

public enum ColorPrimitives {
    public enum White {
        public static let _15 = Color(hex: 0xFFFFFF, alpha: 0.15)
        public static let _25 = Color(hex: 0xFFFFFF, alpha: 0.25)
        public static let _60 = Color(hex: 0xFFFFFF, alpha: 0.6)
        public static let _85 = Color(hex: 0xFFFFFF, alpha: 0.85)
        public static let _100 = Color(hex: 0xFFFFFF)
    }

    public enum Black {
        public static let _15 = Color(hex: 0x000000, alpha: 0.15)
        public static let _25 = Color(hex: 0x000000, alpha: 0.25)
        public static let _60 = Color(hex: 0x000000, alpha: 0.6)
        public static let _85 = Color(hex: 0x000000, alpha: 0.85)
        public static let _100 = Color(hex: 0x000000)
    }

    public enum Sand {
        public static let _100 = Color(hex: 0xFFFDFA)
        public static let _200 = Color(hex: 0xF7F0E6)
        public static let _300 = Color(hex: 0xEADFD0)
        public static let _500 = Color(hex: 0xB8AA99)
    }

    public enum Ink {
        public static let _300 = Color(hex: 0x6B6472)
        public static let _500 = Color(hex: 0x3A3540)
        public static let _700 = Color(hex: 0x26222B)
        public static let _800 = Color(hex: 0x1B181F)
        public static let _900 = Color(hex: 0x121016)
    }

    public enum Orange {
        public static let _100 = Color(hex: 0xFFE6D1)
        public static let _300 = Color(hex: 0xFFB27A)
        public static let _500 = Color(hex: 0xFF7A29)
        public static let _700 = Color(hex: 0xD65A0F)
        public static let _900 = Color(hex: 0x7A3306)
    }

    public enum Teal {
        public static let _300 = Color(hex: 0x7FE0D3)
        public static let _500 = Color(hex: 0x23B5A5)
        public static let _700 = Color(hex: 0x158377)
    }

    public enum Honey {
        public static let _500 = Color(hex: 0xF2B233)
    }

    public enum Red {
        public static let _300 = Color(hex: 0xFF8A80)
        public static let _500 = Color(hex: 0xE53935)
        public static let _700 = Color(hex: 0xB71C1C)
    }

    public enum Green {
        public static let _500 = Color(hex: 0x2ECC71)
    }

    public static let transparent = Color.clear
}

extension Color {
    init(hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xFF) / 255,
            green: Double((hex >> 8) & 0xFF) / 255,
            blue: Double(hex & 0xFF) / 255,
            opacity: alpha
        )
    }
}
