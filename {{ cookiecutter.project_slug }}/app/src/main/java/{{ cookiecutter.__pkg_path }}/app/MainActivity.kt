package {{ cookiecutter.pkg_name }}.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import {{ cookiecutter.pkg_name }}.app.databinding.MainActivityBinding
import {{ cookiecutter.pkg_name }}.app.di.component.NoParamsActivity
import {{ cookiecutter.pkg_name }}.app.utils.requireNavHostFragment
import {{ cookiecutter.pkg_name }}.feature.sample.R as SampleR

class MainActivity : NoParamsActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        val isLaunch = savedInstanceState == null
        super.onCreate(savedInstanceState)
        // AOSP ID 2373: https://issuetracker.google.com/issues/36907463
        if (isTaskRoot.not()) {
            finish()
            return
        }

        if (isLaunch) installSplashScreen()
        enableEdgeToEdge()

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupAppBarConfiguration()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupAppBarConfiguration() {
        val navHostFragment = requireNavHostFragment()
        val navController = navHostFragment.navController
        appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    SampleR.id.sampleFragment,
                ),
            )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = requireNavHostFragment()
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }
}
