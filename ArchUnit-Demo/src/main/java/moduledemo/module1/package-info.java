@MyModuleAnnotation(
        name = "Module 1",
        allowedDependencies = {"Module 2", "Module 3"},
        exposedPackages = {"..module1.api.."}
)
package moduledemo.module1;

import moduledemo.MyModuleAnnotation;
