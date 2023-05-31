package com.example.musicplayerdome.bean;

/**
 * {
 *     "code": 200,
 *     "data": {
 *         "qrurl": "https://music.163.com/login?codekey=",
 *         "qrimg": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJQAAACUCAYAAAB1PADUAAAAAklEQVR4AewaftIAAATdSURBVO3BQY4cRxAEwfDC/P/Lrj2mLgU0OoekqDDDH6laclK16KRq0UnVopOqRSdVi06qFp1ULTqpWnRSteikatFJ1aKTqkUnVYtOqhadVC365CUgv5KaJ4BMaiYgN2omIJOaCcgbaiYgv5KaN06qFp1ULTqpWvTJMjWbgDwBZFLzBpAbIJOaJ4A8oWYTkE0nVYtOqhadVC365MuAPKHmCSCTmhsgT6iZgDwB5JuAPKHmm06qFp1ULTqpWvRJ/YuaCcikZgIyAZnUTEAmNX+Tk6pFJ1WLTqoWffKXATKpmdRMQG7U3KiZgExA/k9OqhadVC06qVr0yZep+Z2AbALyhJobIJOaJ9T8SU6qFp1ULTqpWvTJMiC/k5oJyKRmAnIDZFIzAZnUTEAmNW8A+ZOdVC06qVp0UrXok5fU/EmAfBOQSc2NmjfU/JecVC06qVp0UrUIf+QFIJOaCcgmNU8AuVHzBpAbNROQSc0NkE1qvumkatFJ1aKTqkX4I18E5A01E5BJzRNA3lAzAZnUTEB+JTVPAJnUvHFSteikatFJ1aJPfjM1N0AmNROQSc0EZBOQGyCTmhsgN2omIJvUbDqpWnRSteikatEnLwGZ1DwB5EbNN6mZgExqJiA3am6A3KiZgExqboBMaiYgk5pNJ1WLTqoWnVQt+uTL1ExAngAyqfkmNROQGzUTkEnNjZoJyKTmDSCTmm86qVp0UrXopGrRJy+pmYBMam7UTEAmNb+TmhsgN0BugNwAeULNE0AmNW+cVC06qVp0UrXok5eAfBOQJ4BMam6ATGpugNyomYBMaiYgk5oJyI2aCciNmknNppOqRSdVi06qFn3ykppvUnMDZFIzAZnUTGpugExqnlAzAZnUTEBu1ExAJjU3QCY1m06qFp1ULTqpWvTJHw7IjZpvUvMEkEnNpOYJNROQSc0E5EbNN51ULTqpWnRSteiTl4C8AeRGzQTkDSCTmgnIjZoJyA2QJ9RMQG6ATGomIDdAJjVvnFQtOqladFK16JNlam6A3KiZgNwAeULNG0CeUPMEkEnNBGSTmk0nVYtOqhadVC365MuATGpugExqboBMap4AcqNmAnKjZgLyhJpNaiYgk5pNJ1WLTqoWnVQt+uQPo2YCMql5AsgTaiYgN2omIG8A2QTkBsik5o2TqkUnVYtOqhbhj/yHAZnU3AC5UfMEkBs1N0AmNU8A2aTmjZOqRSdVi06qFn3yEpBfSc2kZgJyo+YNIJOaCcgNkCeATGreUPNNJ1WLTqoWnVQt+mSZmk1AboBMam6A3KiZgNwAmdTcqJmA3Kh5Qs0NkEnNppOqRSdVi06qFn3yZUCeUPNNap5QMwGZ1ExAJjVPANkEZFLzTSdVi06qFp1ULfrkLwPkRs0EZFIzAZnUTEAmNROQN9RMQCY1E5AbIDdq3jipWnRSteikatEnfxk1E5AngExqJiA3QG7UPAHkBsgbajadVC06qVp0UrXoky9T801qbtRMQG7UTEAmNROQSc0NkBs1k5oJyBNqJiDfdFK16KRq0UnVIvyRF4D8SmomIDdqboA8oeYGyKTmCSBPqLkBMqn5ppOqRSdVi06qFuGPVC05qVp0UrXopGrRSdWik6pFJ1WLTqoWnVQtOqladFK16KRq0UnVopOqRSdVi06qFv0DcnEyWDRUhhoAAAAASUVORK5CYII="
 *     }
 * }
 */
public class QrImgBean {
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private DataBean data;
    public class DataBean {
        private String qrurl;
        private String qrimg;

        public String getQrimg() {
            return qrimg;
        }

        public void setQrimg(String qrimg) {
            this.qrimg = qrimg;
        }

        public String getQrurl() {
            return qrurl;
        }

        public void setQrurl(String qrurl) {
            this.qrurl = qrurl;
        }
    }
}
