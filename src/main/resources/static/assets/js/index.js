window.onload = function () {
    counting(12345, "register-tour");
    counting(31158, "register-hotel");
    counting(12415, "register-store");
    counting(32451, "resgister-member");
    counting(1231225, "write-article");

    function counting(num, id) {
        const element = document.getElementById(id);
        if (num == 0) {
            element.innerHTML = "0";
        } else {
            const each = Math.ceil(num / 33);
            let time = 0;
            for (let i = 0; i <= num; i += each) {
                setTimeout(() => {
                    element.innerHTML = i.toLocaleString("ko-KR");
                }, 20 * time);

                if (i + each > this.maxNum1) {
                    setTimeout(() => {
                        element.innerHTML = num.toLocaleString("ko-KR");
                    }, 20 * (time + 1));
                }
                time++;
            }
        }
    }
};

