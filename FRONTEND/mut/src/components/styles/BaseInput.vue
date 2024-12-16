<!-- src/components/BaseInput.vue -->
<template>
  <div class="mb-5">
    <label :for="id" class="block mb-2 text-sm font-medium text-gray-900 text-white">{{ label }}</label>
    <input 
      :type="type" 
      :id="id" 
      :placeholder="placeholder" 
      :value="value" 
      @input="$emit('update:value', $event.target.value)"
      class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
      :required="required"
    />
  </div>
</template>

<script>
export default {
  name: 'BaseInput',
  props: {
    label: {
      type: String,
      required: true
    },
    id: {
      type: String,
      required: true
    },
    type: {
      type: String,
      default: 'text'
    },
    placeholder: {
      type: String,
      default: ''
    },
    value: {
      type: String,
      default: ''
    },
    required: {
      type: Boolean,
      default: false
    }
  }
};
</script>
