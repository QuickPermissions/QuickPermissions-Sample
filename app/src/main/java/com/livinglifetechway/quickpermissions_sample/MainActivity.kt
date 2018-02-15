package com.livinglifetechway.quickpermissions_sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.livinglifetechway.k4kotlin.onClick
import com.livinglifetechway.k4kotlin.setBindingView
import com.livinglifetechway.quickpermissions_sample.databinding.ActivityMainBinding
import com.livinglifetechway.quickpermissions_sample.java.*
import com.livinglifetechway.quickpermissions_sample.kotlin.AllKotlinActivity
import com.livinglifetechway.quickpermissions_sample.kotlin.fragment.FragmentExampleWithKotlinActivity
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = setBindingView(R.layout.activity_main)

        mBinding.buttonJavaAll.onClick {
            startActivity<AutoHandleJavaActivity>()
        }

        mBinding.buttonJavaRationalCallback.onClick {
            startActivity<ManageRationaleJavaActivity>()
        }

        mBinding.buttonJavaPerDeniedCallback.onClick {
            startActivity<ManagePeranentlyDeniedJavaActivity>()
        }

        mBinding.buttonJavaAllCallback.onClick {
            startActivity<HandleAllCallbackJavaActivity>()
        }

        mBinding.buttonJavaCustomMessage.onClick {
            startActivity<CustomMessageJavaActivity>()
        }

        mBinding.buttonJavaDoNotHandle.onClick {
            startActivity<DoNotHandleJavaActivity>()
        }

        mBinding.buttonKotlinAll.onClick {
            startActivity<AllKotlinActivity>()
        }

        mBinding.buttonKotlinFragment.onClick {
            startActivity<FragmentExampleWithKotlinActivity>()
        }
    }
}
