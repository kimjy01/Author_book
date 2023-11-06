const ul = document.querySelector('ul');
const liElements = ul.querySelectorAll('li');
const lastLi = liElements[liElements.length - 1];
const hrElement = lastLi.querySelector('hr');
if (hrElement) {
    lastLi.removeChild(hrElement);
}