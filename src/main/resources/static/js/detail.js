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

        this.moreDescriptionButton = $('.store_details');
        this.openDescriptionButton = $('#open_button');
        this.closeDescriptionButton = $('#close_button');

    }

    addEventListeners() {
        $('.prev_inn').on('click', this.prevEvent.bind(this));
        $('.nxt_inn').on('click', this.nextEvent.bind(this));
        $('.bk_more').on('click', this.descriptionEvent.bind(this));
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
        $('.visual_img').css('transition', '0.5s ease-in');
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

    descriptionEvent() {
        if (this.moreDescriptionButton.hasClass('close3')) { //펼쳐지지 않은 기본 상태
            this.moreDescriptionButton.removeClass('close3');
            this.openDescriptionButton.css('display', 'none');
            this.closeDescriptionButton.css('display', 'block');
        } else {  //펼쳐진 상태
            this.moreDescriptionButton.addClass('close3');
            this.closeDescriptionButton.css('display', 'none');
            this.openDescriptionButton.css('display', 'block');
        }

        // if($('.store_details').hasClass('close3')){ //펼쳐지지 않은 기본 상태
        //     $('.store_details').removeClass('close3');
        //     $('.bk_more, ._open').css('display','none');
        //     $('.bk_more, ._close').css('display','block');
        // }else{  //펼쳐진 상태
        //     $('.store_details').addClass('close3');
        //     $('.bk_more, ._open').css('display','block');
        //     $('.bk_more, ._close').css('display','none');
        // }
    }
}