import Foundation

func findProjectRoot(from directory: String) -> String? {
    let fileManager = FileManager.default
    var currentDirectory = directory

    while true {
        if let contents = try? fileManager.contentsOfDirectory(atPath: currentDirectory) {
            for item in contents {
                if item.hasSuffix(".xcodeproj") || item.hasSuffix(".xcworkspace") {
                    return currentDirectory
                }
            }
        }

        let parentDirectory = (currentDirectory as NSString).deletingLastPathComponent
        if parentDirectory == currentDirectory {
            break
        }
        currentDirectory = parentDirectory
    }

    return nil
}

func findSwiftFiles(in directory: String) -> [String] {
    var swiftFiles: [String] = []
    if let contents = try? FileManager.default.contentsOfDirectory(atPath: directory) {
        for item in contents.sorted() {
            let itemPath = "\(directory)/\(item)"
            var isDirectory: ObjCBool = false
            if FileManager.default.fileExists(atPath: itemPath, isDirectory: &isDirectory) {
                if isDirectory.boolValue {
                    swiftFiles.append(contentsOf: findSwiftFiles(in: itemPath))
                } else if item.hasSuffix(".swift") {
                    swiftFiles.append(itemPath)
                }
            }
        }
    }
    return swiftFiles
}

var generatedFilePaths: [String] = []

func writeToFile(_ content: String, to file: String) {
    do {
        try content.write(toFile: file, atomically: true, encoding: .utf8)
        generatedFilePaths.append(file)
    } catch {
        print("Failed writing to file \(file)")
    }
}

func runSwiftFormat(on file: String) {
    let process = Process()
    process.executableURL = URL(fileURLWithPath: "/usr/bin/env")
    process.arguments = ["swift", "format", "--in-place", file]
    do {
        try process.run()
        process.waitUntilExit()
        print("Formatted \(file)")
    } catch {
        print("Failed to run swift format on \(file): \(error)")
    }
}
