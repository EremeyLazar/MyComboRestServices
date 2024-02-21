document.getElementById('numberInput').addEventListener('input', function () {
    var inputValue = this.value;
    var isValid = /^([0-9]|[1-9][0-9]|100)$/.test(inputValue);

    this.setCustomValidity(isValid ? '' : 'Please enter a number between 0 and 100.');
    this.classList.toggle('is-invalid', !isValid);
});