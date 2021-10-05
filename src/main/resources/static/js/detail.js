document.addEventListener("DOMContentLoaded", initDetailPage);

async function initDetailPage() {
    // const displayInfoId = location.search.split("=")[1].replace("#","");

    const displayInfoId = $('#container').attr('data-displayInfoId');

    let titleArea = new TitleArea();
    let titleData = await titleArea.requestTitleData(displayInfoId);
    console.log(titleData);

    titleArea.drawTitleImage(titleData);

}

class TitleArea {
    constructor(displayInfoId) {
        this.width = 414;
        this.template = $('#detailTitleTemplate').html();

        this.addEventListeners();

    }

    addEventListeners() {
        $('.prev_inn').on('click', this.prevEvent.bind(this));
        $('.nxt_inn').on('click', this.nextEvent.bind(this));
    }

    requestTitleData(displayInfoId) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/api/detailData/" + displayInfoId,
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("promotion data load failed.");
                }
            });
        });
    }

    drawTitleImage(titleData) {
        let bindTemplate = Handlebars.compile(this.template);
        let resultHTML = "";
        for (let tData of titleData) {
            resultHTML += bindTemplate(tData);
        }

        resultHTML += bindTemplate(titleData[0]);

        $('.visual_img').html(resultHTML);

        $('.store_details .dsc').html(titleData[0].content);

        $('.num span').html(titleData.length);

        //기타 이미지가 없을 때 이전,다음 이미지 화살표 지우기
        if (titleData.length === 1) {
            $('.prev_inn').css('display', 'none');
            $('.nxt_inn').css('display', 'none');
        }

        $('.visual_img').css('left', '0px');
    }

    prevEvent() {
        let nowLeft = parseInt($('.visual_img').css('left'));
        if (nowLeft === 0) {    //첫번째 이미지를 의미
            return;
        }

        $('.visual_img').css('left', (nowLeft + this.width) + "px");

        $('.prev_inn').css('display', 'none');
        $('.nxt_inn').css('display', 'inline-block');
    }

    nextEvent() {
        let nowLeft = parseInt($('.visual_img').css('left'));
        if (nowLeft === -this.width) {  //마지막 이미지를 의미
            return;
        }

        $('.visual_img').css('left', (nowLeft - this.width) + "px");

        $('.nxt_inn').css('display', 'none');
        $('.prev_inn').css('display', 'inline-block');
    }
}