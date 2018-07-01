-printmapping out/proguard/mapping.txt

-verbose

-optimizationpasses 3
-overloadaggressively
-repackageclasses ''
-allowaccessmodification
-dontwarn

-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}