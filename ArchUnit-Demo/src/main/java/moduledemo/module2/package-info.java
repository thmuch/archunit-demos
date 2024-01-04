@MyModuleAnnotation(
        name = "Module 2",
        allowedDependencies = {"Module 3"},
        exposedPackages = {"..module2.api.."}
)
package moduledemo.module2;

import moduledemo.MyModuleAnnotation;
