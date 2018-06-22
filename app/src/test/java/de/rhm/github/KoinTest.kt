
import de.rhm.github.AppModule
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.dryRun

class MyTest : AutoCloseKoinTest() {

    val TestModule = applicationContext {
        bean { Schedulers.trampoline() }
    }

    @Test
    fun dryRunTest() {
        // start Koin
        startKoin(listOf(AppModule, TestModule))
        // dry run of given module list
        dryRun()
    }
}