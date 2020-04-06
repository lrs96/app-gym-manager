package br.com.app_my_manage_mobile

import android.content.DialogInterface
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.app_my_manage_mobile.R
import br.com.app_my_manage_mobile.TipoMsg


fun showMsgToast(activity: AppCompatActivity, txt: String) {
    var inflater = activity.layoutInflater
    var lytToast = inflater.inflate(R.layout.toast_template, activity.findViewById(R.id.lytToast))

    var txtToast = lytToast.findViewById<TextView>(R.id.txtToast)
    txtToast.setText(txt)

    //Envia texto na tela
    var toast = Toast(activity)
    toast.view = lytToast
    toast.show()
}

fun showMsgAlerta(activity: AppCompatActivity, titulo: String, txt: String, tipoMsg: TipoMsg) {
    var theme:Int
    var incone:Int


    when (tipoMsg) {
        TipoMsg.info -> {
            theme = R.style.AppTheme_Dark_Dialog_Info
            incone = R.drawable.info_alert
        }
        TipoMsg.erro -> {
            theme = R.style.AppTheme_Dark_Dialog_Erro
            incone = R.drawable.error_alert
        }
        TipoMsg.sucesso ->{
            theme = R.style.AppTheme_Dark_Dialog_Sucess
            incone = R.drawable.sucess_alert

        }
        else ->{
            theme = R.style.AppTheme_Dark_Dialog_Info
            incone = R.drawable.info_alert

        }
    }

    var alertDialog = AlertDialog.Builder(activity, theme).create()
    with(alertDialog) {
        setTitle(titulo)
        setMessage(txt)
        setIcon(incone)

        setButton(
        DialogInterface.BUTTON_POSITIVE,
        "OK",
        DialogInterface.OnClickListener {
                _: DialogInterface?, _: Int ->
                    showMsgToast(activity, "Test App v1")
                    alertDialog.dismiss()
        })
    }

    var params = LayoutParams()
    params.copyFrom(alertDialog.window?.attributes)
    params.width = LayoutParams.MATCH_PARENT
    params.height = LayoutParams.WRAP_CONTENT
    alertDialog.show()
    alertDialog.window?.setAttributes(params)

}


