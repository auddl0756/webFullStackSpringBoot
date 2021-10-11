document.addEventListener("DOMContentLoaded", initDetailPage);

async function initDetailPage() {
    // const displayInfoId = location.search.split("=")[1].replace("#","");

    const displayInfoId = $('#container').attr('data-displayInfoId');

    let titleArea = new TitleArea();
    let titleData = await titleArea.requestTitleData(displayInfoId);

    titleArea.drawTitleImage(titleData);

    let commentArea = new CommentArea();
    let commentData = await commentArea.requestInitialCommentData(displayInfoId);

    commentArea.drawGrade(parseFloat(commentData.averageScore), commentData.comments.length);
    commentArea.drawComments(commentData.comments);

    let bottomTabArea = new BottomTabArea();
}

class TitleArea {
    constructor(displayInfoId) {
        this.width = 414;
        this.template = $('#detailTitleTemplate').html();

        this.titleImageSection = $('.visual_img');

        this.prevButton = $('.prev_inn');
        this.nextButton = $('.nxt_inn');

        this.moreDescriptionButton = $('.bk_more');
        this.descriptionSection = $('.store_details');
        this.openDescriptionButton = $('#open_button');
        this.closeDescriptionButton = $('#close_button');

        this.addEventListeners();
    }

    addEventListeners() {
        this.prevButton.on('click', this.prevEvent.bind(this));
        this.nextButton.on('click', this.nextEvent.bind(this));
        this.moreDescriptionButton.on('click', this.descriptionEvent.bind(this));
    }

    requestTitleData(displayInfoId) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/api/detailTitleData/" + displayInfoId,
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("detail title data load failed.");
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

        this.titleImageSection.html(resultHTML);

        $('.store_details > .dsc').html(titleData[0].content);

        $('.num span').html(titleData.length);

        //기본적으로 처음에 1번째 타이틀 이미지니까 prev 버튼 보여주지 않기.
        this.prevButton.css('display', 'none');

        //기타 이미지가 없을 때 다음 이미지 화살표 지우기
        if (titleData.length === 1) {
            this.nextButton.css('display', 'none');
        }

        this.titleImageSection.css('left', '0px');
        this.titleImageSection.css('transition', '0.5s ease-in');
    }

    prevEvent() {
        let nowLeft = parseInt(this.titleImageSection.css('left'));
        if (nowLeft === 0) {    //첫번째 이미지를 의미
            return;
        }

        this.titleImageSection.css('left', (nowLeft + this.width) + "px");

        this.prevButton.css('display', 'none');
        this.nextButton.css('display', 'inline-block');
    }

    nextEvent() {
        let nowLeft = parseInt(this.titleImageSection.css('left'));
        if (nowLeft === -this.width) {  //마지막 이미지를 의미
            return;
        }

        this.titleImageSection.css('left', (nowLeft - this.width) + "px");

        this.nextButton.css('display', 'none');
        this.prevButton.css('display', 'inline-block');
    }

    descriptionEvent() {
        if (this.descriptionSection.hasClass('close3')) { //펼쳐지지 않은 기본 상태
            this.descriptionSection.removeClass('close3');
            this.openDescriptionButton.css('display', 'none');
            this.closeDescriptionButton.css('display', 'block');
        } else {  //펼쳐진 상태
            this.descriptionSection.addClass('close3');
            this.closeDescriptionButton.css('display', 'none');
            this.openDescriptionButton.css('display', 'block');
        }
    }
}

class CommentArea {
    constructor() {
        this.commentArea = $('.list_short_review');
        this.commentWithImageTemplate = $('#commentItemWithImageTemplate').html();
        this.commentWithNoImageTemplate = $('#commentItemWithNoImageTemplate').html();

        this.gradeGraphValue = $('.graph_value');
        this.gradeTextValue = $('.text_value > span');
        this.totalCommentCountArea = $('.join_count > .green');

    }

    requestInitialCommentData(displayInfoId) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/api/comments/initial/" + displayInfoId,
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("detail comment data load failed.");
                }
            });
        });
    }

    drawGrade(averageScore, totalCount) {
        this.gradeGraphValue.css('width', 100 * averageScore / 5.0 + "%");
        this.gradeTextValue.html(averageScore.toFixed(1));
        this.totalCommentCountArea.html(totalCount + "건");
    }

    drawComments(commentData) {
        let commentWithImageBindTemplate = Handlebars.compile(this.commentWithImageTemplate);
        let commentWithNoImageBindTemplate = Handlebars.compile(this.commentWithNoImageTemplate);

        let resultHTML = "";
        for (let cData of commentData) {
            if (cData.image !== null) {
                cData.saveFileName = cData.image.saveFileName;
                resultHTML += commentWithImageBindTemplate(cData);
            } else {
                resultHTML += commentWithNoImageBindTemplate(cData);
            }
        }

        this.commentArea.html(resultHTML);
    }
}

class BottomTabArea {
    constructor() {
        this.tabArea = $('.info_tab_lst');
        this.itemDetailTabAnchor = $('._detail > .anchor');
        this.itemPathTabAnchor = $('._path > .anchor');
        this.tabArea.on('click', this.toggleTabEvent.bind(this));

        this.detailTabContent = $('.detail_area_wrap');
        this.pathTabContent = $('.path_area_wrap');
    }

    toggleTabEvent(target) {
        let liTag = target.target.closest('li');

        if (liTag.classList.contains('_detail')) {
            this.itemDetailTabAnchor.addClass('active');
            this.itemPathTabAnchor.removeClass('active');

            this.detailTabContent.removeClass('hide');
            this.pathTabContent.addClass('hide');
        } else if (liTag.classList.contains('_path')) {
            this.itemPathTabAnchor.addClass('active');
            this.itemDetailTabAnchor.removeClass('active');

            this.detailTabContent.addClass('hide');
            this.pathTabContent.removeClass('hide');
        }
    }


}