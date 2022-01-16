package io.github.dytroInc.sample

import org.apache.commons.lang.reflect.ConstructorUtils
import org.bukkit.Bukkit
import java.lang.reflect.InvocationTargetException

// Library Loader (https://github.com/monun/paper-sample/blob/master/sample-api/src/main/kotlin/io/github/monun/sample/LibraryLoader.kt)
object LibraryLoader {
    @Suppress("UNCHECKED_CAST")
    fun <T> loadImplement(type: Class<T>, vararg initArgs: Any? = emptyArray()): T {
        val packageName = type.`package`.name
        val className = "${type.simpleName}Impl"
        val parameterTypes = initArgs.map { it?.javaClass }.toTypedArray()

        return try {
            val internalClass =
                Class.forName("$packageName.internal.$className", true, type.classLoader).asSubclass(type)
            val constructor = ConstructorUtils.getMatchingAccessibleConstructor(internalClass, parameterTypes)
                ?: throw UnsupportedOperationException("${type.name} does not have Constructor for [${parameterTypes.joinToString()}]")
            constructor.newInstance(*initArgs) as T
        } catch (exception: ClassNotFoundException) {
            throw UnsupportedOperationException("${type.name} a does not have implement", exception)
        } catch (exception: IllegalAccessException) {
            throw UnsupportedOperationException("${type.name} constructor is not visible")
        } catch (exception: InstantiationException) {
            throw UnsupportedOperationException("${type.name} is abstract class")
        } catch (exception: InvocationTargetException) {
            throw UnsupportedOperationException(
                "${type.name} has an error occurred while creating the instance",
                exception
            )
        }
    }
}
