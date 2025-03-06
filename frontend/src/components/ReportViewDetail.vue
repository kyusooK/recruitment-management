<template>

    <v-card outlined>
        <v-card-title>
            Report # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="TotalApplyCount" v-model="item.totalApplyCount" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="PassedCount" v-model="item.passedCount" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ReportViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/reports/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

